package com.schoolfam.parcher.Fragments.TeacherFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.schoolfam.parcher.Fragments.AdminFragments.AddStudentsFragment

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.assessment.Assessment
import com.schoolfam.parcher.data.assessmentType.AssessmentType
import com.schoolfam.parcher.data.subject.Subject
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.synthetic.main.fragment_teacher_post_assessment.*


class TeacherPostAssessmentFragment : Fragment() {

    private lateinit var studentViewModel: StudentViewModel
    private lateinit var parntViewModel: ParentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var sectionViewModel: SectionViewModel
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var assessmentViewModel: AssessmentViewModel
    private lateinit var assessmentTypeViewModel: AssessmentTypeViewModel
    private lateinit var sectionSubjectViewModel: SectionSubjectViewModel

    //private lateinit var sectionViewModel: Assess

    var student:User? = null
    var sectionId:Long? = null


    private lateinit var studentNameTextView: TextView
    private lateinit var subjectSpinner: Spinner
    private lateinit var assessmentTypeSpinner: Spinner
    private lateinit var postAssessmentButton: Button
    private lateinit var selectSubjectButton: Button
    private lateinit var scoreEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_post_assessment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        parntViewModel = ViewModelProviders.of(this).get(ParentViewModel::class.java)
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        sectionViewModel = ViewModelProviders.of(this).get(SectionViewModel::class.java)
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)
        assessmentViewModel = ViewModelProviders.of(this).get(AssessmentViewModel::class.java)
        assessmentTypeViewModel = ViewModelProviders.of(this).get(AssessmentTypeViewModel::class.java)
        sectionSubjectViewModel = ViewModelProviders.of(this).get(SectionSubjectViewModel::class.java)


        student = arguments?.getSerializable("current_student") as User?
        sectionId = arguments?.getLong("section_id")


        studentNameTextView = assessment_student_name
        subjectSpinner = subject_spinner
        assessmentTypeSpinner = assessment_type_spinner
        postAssessmentButton = post_assessment_button
        scoreEditText = assessment_score_edit_text
        selectSubjectButton = select_subject_button

        studentNameTextView.text = student!!.fname+" "+ student!!.lname

       sectionSubjectViewModel.findSectionSubjectBySectionId(sectionId!!).observe(this, Observer {
           sectionSubject -> sectionSubject?.let {
           val subjects:MutableList<Subject> = mutableListOf()

           sectionSubject.forEach {
               sectionSubject ->sectionSubject?.let {
               subjectViewModel.findSubjectById(sectionSubject.subjectId).observe(this, Observer {
                       subject-> subjects.add(subject)
                   val adapter: ArrayAdapter<Subject> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,subjects)
                   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                   subjectSpinner.adapter = adapter
               })
           }

           }
       }
       })

        selectSubjectButton.setOnClickListener {

            assessmentTypeViewModel.allAssessmentTypes.observe(this, Observer { assessmentTypes -> assessmentTypes?.let {
                val allAssessmentTypes:MutableList<AssessmentType> = mutableListOf()
                assessmentTypes.forEach { assessmentType->
                    assessmentViewModel.findAssessmentByStudentIdAndSubjectId((subjectSpinner.selectedItem as Subject).id,student!!.id!!).observe(this,
                        Observer { assessments->
                            Toast.makeText(activity,"Number Of Assessment Type: "+assessments.size,Toast.LENGTH_LONG).show()
                            if (assessments.isEmpty()){
                                val adapter: ArrayAdapter<AssessmentType> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,assessmentTypes)
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                assessmentTypeSpinner.adapter = adapter
                            }
                            else{
                               val cAssessment = assessments.find { assessment-> assessment.assessmentTypeId == assessmentType.id}
                                if (cAssessment==null){
                                    allAssessmentTypes.add(assessmentType)
                                    val adapter: ArrayAdapter<AssessmentType> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,allAssessmentTypes)
                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                    assessmentTypeSpinner.adapter = adapter
                                }
//                                assessments.forEach {assessment->
//                                    if (assessment.assessmentTypeId != assessmentType.id){
//
//                                    }
//                                }
                            }


                        }
                    )
                }

            } })
        }



        postAssessmentButton.setOnClickListener {

            if (scoreEditText.text.toString().startsWith(" ")||scoreEditText.text.toString() == "")
            {
                Snackbar.make(it, "Please Enter Assessment Score", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }

            else{
                val subject = subjectSpinner.selectedItem as Subject
                val assessmentType = assessmentTypeSpinner.selectedItem as AssessmentType
                val score = scoreEditText.text.toString().toDouble()

                if(assessmentType.id == 1L && scoreEditText.text.toString().toDouble()>10)
                {
                    Snackbar.make(it, "Maximum Score Allowed For Test 1: 10", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
                else if(assessmentType.id == 2L && scoreEditText.text.toString().toDouble()>10)
                {
                    Snackbar.make(it, "Maximum Score Allowed For For Test 2: 10", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
                else if(assessmentType.id == 3L && scoreEditText.text.toString().toDouble()>10)
                {
                    Snackbar.make(it, "Maximum Score Allowed For Assignment 1: 10", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
                else if(assessmentType.id == 4L && scoreEditText.text.toString().toDouble()>10)
                {
                    Snackbar.make(it, "Maximum Score Allowed For Assignment 2: 10", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
                else if(assessmentType.id == 5L && scoreEditText.text.toString().toDouble()>20)
                {
                    Snackbar.make(it, "Maximum Score Allowed For Mid Exam: 20", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
                else if(assessmentType.id == 6L && scoreEditText.text.toString().toDouble()>40)
                {
                    Snackbar.make(it, "Maximum Score Allowed For Final Exam: 40", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
                else{
                    val newAssessment = Assessment(subject.id,student!!.id!!,assessmentType.id,score)
                    assessmentViewModel.insertAssessment(newAssessment)

                    Snackbar.make(it, "Assessment Successfully Posted", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                    val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                    val fragment = TeacherPostAssessmentFragment()
                    val bundle = Bundle()
                    bundle.putSerializable("current_student",student)
                    bundle.putLong("section_id",sectionId!!)
                    fragment.arguments = bundle
                    fragmentTransaction.replace(R.id.teacher_frame_layout, fragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }

                }


            }



        }



    }
